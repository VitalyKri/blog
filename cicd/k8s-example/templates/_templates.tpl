{{- define "decoder.deployment" -}} #Именованный шаблон (типо макрос) имя decoder.deployment
# - удаляет все пробел в начале строки и в конце, как и переход строки

apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ .appName }}-deployment # . в начале переменной это контекст, это yml карта, которая передается на вход шаблону
  namespace: {{ .namespace}} #здесь контекст ниже
  labels:
    app: {{ .appName }}
spec:
  replicas: {{ .replicasCount}}
  selector:
    matchLabels:
      app: {{ .appName }}
  template:
    metadata:
      labels:
        app: {{ .appName }}
    spec:
      containers:
        - name: {{ .appName }}
          image: {{ .container.image }}:{{ .container.version }}
          ports:
            - containerPort: {{ .container.port }}
          resources: #лимиты позволят ограничить ресурсы
            requests:
              memory: 256M
              cpu: 100m
            limits:
              memory: 256M
              cpu: 200m
{{- if .container.env }}
          env:
{{- toYaml .container.env | indent 8 }} #преобразует мапу, indent 8  сдвинет на 8 пробелов
{{- end}}
{{- end}}

{{- define "decoder.service"}}
{{ if .service}}
apiVersion: v1
kind: Service
metadata:
  name: {{ .appName}}
  namespace: {{ .Values.namespace}}
spec:
  #с Ingress не нужен открывать сервис во вне, все делает он
  {{ if .service.type}} type: {{ .service.type}} {{ end}} #имеет внешний IP адрес в отличии от кластер IP
  selector:
    app: {{ .appName}}
  ports:
    - protocol: TCP
      port: {{ .service.port }}
      targetPort: {{ .service.targetPort }}
      #с Ingress не нужен открывать сервис во вне, все делает он
      {{ if .service.nodePort}} nodePort: {{ .service.nodePort}} {{ end}} #если порт есть добавляем его
      #nodePort: 30000 #порт который будет открыт на физ машине он должен быть в диапазоне от 30000-32767
{{- end}}
{{- end}}

