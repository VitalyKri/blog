
{{- define "app.deployment"}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{- include "app.name" $}}-deployment
  namespace: {{ .val.namespace}}
  labels:
    {{- include "app.labels" $ | nindent 4 }}
spec:
  replicas: {{ .val.replicasCount}}
  selector:
    matchLabels:
      {{- include "app.selectorLabels" $ | nindent 6 }}
  template:
    metadata:
      {{- if or .val.secretMaps .val.configMaps .val.filesConfig -}}
      {{- include "app.annotationsChecksum" $ | nindent 6 }}
      {{- end}}
      labels:
        {{- include "app.labels" $ | nindent 8 }}
    spec:
      containers:
        - name: {{ .val.name }}
          image: {{ .val.container.image }}:{{ .val.container.version }}
          ports:
            - containerPort: {{ .val.container.port }}
          resources:
            requests:
              memory: 256M
              cpu: 100m
            limits:
              memory: 256M
              cpu: 200m
          {{- if .val.container.command }}
          command:
            {{- toYaml .val.container.command | nindent 12 }}
          {{- end}}
          {{- if .val.container.args }}
          args:
            {{- toYaml .val.container.args | nindent 12 -}}
          {{end -}}
          {{- if .val.container.env }}
          env:
            {{ toYaml .val.container.env | indent 8 }}
            {{ include "app.envSecret" $ | indent 10 -}}
          {{- end}}
          {{- include "app.envFrom" $ | nindent 10 -}}
       {{- include "app.filesConfig" $ | indent 6 }}
{{end}}

{{/*
Сервис
*/}}

{{- define "app.service"}}
{{- if .val.service}}
apiVersion: v1
kind: Service
metadata:
  {{- if .val.service.name }}
  name: {{ .val.service.name}}
  {{- else}}
  name: {{- include "app.name" $}}-svc
  {{- end}}
  namespace: {{ .val.namespace}}
spec:
  {{- if .val.service.type}}
  type: {{ .val.service.type}}
  {{- end}}
  selector:
    {{- include "app.selectorLabels" $ | nindent 4 }}
  ports:
    - protocol: TCP
      port: {{ .val.service.port }}
      targetPort: {{ .val.service.targetPort }}
      {{- if and (eq .val.service.type "NodePort") .val.service.nodePort }}
      nodePort: {{ .val.service.nodePort }}
      {{ end }}
{{ end}}
{{ end }}




{{- define "app.config"}}
{{- if .val.name }}
apiVersion: v1
kind: ConfigMap
metadata:
  name: {{ .val.name}}
  namespace: {{ .all.Values.namespace}}
  labels:
    {{- include "app.labels" $ | nindent 4 }}
data:
{{- if .val.data }}
{{- range $key ,$val := .val.data -}}
{{ $key|nindent 2}}: {{$val| quote }}
{{- end -}}
{{- end }}
{{ end }}
{{ end }}

{{- define "app.secret"}}
{{- if .val.name }}
apiVersion: v1
data:
{{- if .val.data }}
{{- range $key ,$val := .val.data -}}
{{ $key|nindent 2}}: {{ print $val  |b64enc }}
{{- end -}}
{{- end }}
kind: Secret
metadata:
  name: {{ .val.name}}
  namespace: {{ .all.Values.namespace}}
  labels:
    {{- include "app.labels" $ | nindent 4 }}
{{ end }}
{{ end }}
