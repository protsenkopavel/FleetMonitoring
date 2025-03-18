** тут будет ридми **

локальный разворот кубера:
1. Разворот реестра
- docker run -d -p 5000:5000 --restart=always --name registry registry:2
2. Запуск локального кластера kubernetes с помощью minikube с использованием docker в качестве драйвера,
с разрешением использовать локальный реестр контейнерных образов без проверки TLS
- minikube start --driver=docker --insecure-registry="192.168.49.1/24" --addons="ingress" --cpus=8