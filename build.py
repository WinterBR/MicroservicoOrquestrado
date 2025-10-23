import os
import threading
import time
import subprocess

apps = {
    "order-service": "OrderService",
    "orchestrator-service": "OrchestratorService",
    "product-validation-service": "ProductValidationService",
    "payment-service": "PaymentService",
    "inventory-service": "InventoryService"
}

def build_application(service_name, folder_name):
    print(f"🚧 Building application {service_name} ({folder_name})...")
    subprocess.run(f"cd {folder_name} && mvn clean package -DskipTests", shell=True, check=True)
    print(f"✅ Application {service_name} finished building!")

def build_all_applications():
    threads = []
    for service, folder in apps.items():
        t = threading.Thread(target=build_application, args=(service, folder))
        t.start()
        threads.append(t)
    for t in threads:
        t.join()

def remove_remaining_containers():
    print("🧹 Stopping and removing containers...")
    subprocess.run("docker compose down", shell=True)
    subprocess.run("docker container prune -f", shell=True)

def docker_compose_up():
    print("🐳 Starting Docker Compose...")
    subprocess.run("docker compose up --build -d", shell=True)
    print("🎯 Pipeline finished successfully!")

if __name__ == "__main__":
    print("🚀 Pipeline started!")
    build_all_applications()
    remove_remaining_containers()
    time.sleep(3)
    docker_compose_up()
