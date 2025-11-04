
# Integración API REST (OpenWeather) – TravelGo

## Cómo usar
1. En `local.properties` agrega tu clave:
```
OPEN_WEATHER_API_KEY=TU_CLAVE_AQUI
```
2. Sincroniza Gradle y ejecuta.
3. Navega a la ruta **"apiDemo"** en la app para probar la consulta de clima por ciudad.

## Técnicos
- Retrofit + OkHttp + Logging + Gson
- Repository + ViewModel (StateFlow)
- Compose UI con estados: loading / success / error

> Si `BuildConfig.OPEN_WEATHER_API_KEY` vale **REPLACE_ME**, configura la clave para que funcione.
