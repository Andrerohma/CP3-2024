import com.example.caremi_kotlin.api.PlantaApiService
import com.example.caremi_kotlin.database.PlantaDao
import com.example.caremi_kotlin.model.Planta

class PlantaRepository(private val plantaDao: PlantaDao, private val apiService: PlantaApiService) {

    suspend fun getPlantas(): List<Planta> {
        // Obtenha plantas da API e atualize o banco de dados local
        val response = apiService.getPlantas()
        return if (response.isSuccessful) {
            response.body()?.also { plantas ->
                plantas.forEach { plantaDao.insert(it) }
            } ?: emptyList()
        } else {
            plantaDao.getAll() // Retorna dados locais caso falhe a chamada de API
        }
    }

    suspend fun addPlanta(planta: Planta): Boolean {
        val response = apiService.createPlanta(planta)
        return if (response.isSuccessful) {
            response.body()?.let { plantaDao.insert(it) }
            true
        } else {
            false
        }
    }

    suspend fun updatePlanta(planta: Planta): Boolean {
        planta.id?.let { id ->
            val response = apiService.updatePlanta(id, planta)
            if (response.isSuccessful) {
                plantaDao.update(planta)
                return true
            }
        }
        return false
    }

    suspend fun deletePlanta(id: Int): Boolean {
        val response = apiService.deletePlanta(id)
        return if (response.isSuccessful) {
            plantaDao.deleteById(id)
            true
        } else {
            false
        }
    }
}