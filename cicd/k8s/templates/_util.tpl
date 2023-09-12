
{{/*Возврат чексуммы по конфигам*/}}

{{- define "app.annotationsChecksum" -}}
{{- if or .val.configMaps .val.secretMaps .val.filesConfig  -}}
annotations:
{{- if .val.configMaps }}
{{- range $val := .val.configMaps }}
  {{- $myVal := dict "target"  $val "list" $.all.Values.config }}
  checksum_{{$val}} : {{ include "app.mapValueInList" $myVal | toYaml| sha256sum }}
{{- end -}}
{{end -}}
{{- if .val.secretMaps }}
{{- range $val := .val.secretMaps }}
  {{- $myVal := dict "target"  $val "list" $.all.Values.secret }}
  checksum_{{$val}} : {{ include "app.mapValueInList" $myVal | toYaml| sha256sum }}
{{- end -}}
{{end -}}
{{- if .val.filesConfig }}
{{- range $val := .val.filesConfig }}
  checksum_{{$val.name}} : {{ include (printf "%s/%s.yml" $.all.Template.BasePath $val.name) $.all  | sha256sum }}
{{- end -}}
{{end -}}

{{- end -}}
{{end}}

{{/*Возврат мапы по ключу из списка параметры: .target .list*/}}
{{- define "app.mapValueInList" -}}

{{range $val := .list -}}
{{if eq $.target $val.name }}
{{ toYaml $val }}
{{end -}}
{{- end}}
{{end}}



{{/*Добавление переменных окружения*/}}

{{- define "app.envFrom" -}}
{{- if or .val.secretMaps .val.configMaps -}}
envFrom:
{{- if .val.configMaps }}
{{- range $val := .val.configMaps }}
  - configMapRef:
      name: {{ $val}}
{{- end}}
{{- end}}
{{- if .val.secretMaps }}
{{- range $val := .val.secretMaps }}
  - secretRef:
      name: {{$val}}
{{- end}}
{{- end }}
{{- end -}}
{{- end}}

{{/*префикса файла конфига*/}}
{{- define "app.filesConfig" -}}
{{- if $.val.configMounts }}
    volumeMounts:
{{- $files := $.all.Files }}
  {{- range $val := $.val.configMounts }}
    {{- $wildcard := printf "%s/**" $val.name }}
    {{- $shortPath := printf "%s/" $val.name }}
    {{- range $path, $_ := $.all.Files.Glob $wildcard }}
      - name: {{ $val.name }}
        mountPath: {{  printf "%s/%s" $val.mountPath ($path | trimPrefix $shortPath) }}
        subPath: {{ $path |sha256sum | quote }}
    {{- end}}
  {{- end }}
volumes:
{{- range $val := .val.configMounts }}
  - name: {{ $val.name }}
    configMap:
      name: {{ $val.name }}
{{- end}}
{{- end}}
{{- end }}


{{/*
Заголовок
*/}}
{{- define "app.name" -}}
{{- printf " %s-%s" .all.Values.projectName .val.name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Имя
*/}}
{{- define "app.nameByName" -}}
{{- printf "%s-%s" .project .name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
имя чарта
*/}}
{{- define "app.chart" -}}
{{- printf " %s-%s" .all.Chart.Name .all.Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}


{{/*
Common labels
*/}}
{{- define "app.labels" -}}
{{ include "app.selectorLabels" . }}
{{- if .all.Chart.AppVersion }}
app.kubernetes.io/version: {{ .all.Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .all.Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "app.selectorLabels" -}}
app.kubernetes.io/name:{{ include "app.name" . }}
app.kubernetes.io/instance: {{ .all.Release.Name }}
{{- end }}



