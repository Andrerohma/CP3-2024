
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caremi_kotlin.api.RetrofitClient
import com.example.caremi_kotlin.model.Planta
import com.example.caremi_kotlin.repository.PlantaRepository
import kotlinx.coroutines.launch

class PlantaViewModel : ViewModel() {

    private val plantaRepository = PlantaRepository(RetrofitClient.instance)

    // Lista de plantas
    private val _plantas = MutableLiveData<List<Planta>>()
    val plantas: LiveData<List<Planta>> = _plantas

    // Status de erro
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    // Status de sucesso para operações de criação, atualização e exclusão
    private val _operationSuccess = MutableLiveData<Boolean>()
    val operationSuccess: LiveData<Boolean> = _operationSuccess

    // Carrega todas as plantas
    fun loadPlantas() {
        viewModelScope.launch {
            try {
                val response = plantaRepository.getAllPlantas()
                if (response.isSuccessful && response.body() != null) {
                    _plantas.value = response.body()
                    _error.value = null
                } else {
                    _error.value = "Erro ao carregar plantas: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Erro ao carregar plantas: ${e.message}"
            }
        }
    }

    // Cria uma nova planta
    fun createPlanta(planta: Planta) {
        viewModelScope.launch {
            try {
                val response = plantaRepository.createPlanta(planta)
                _operationSuccess.value = response.isSuccessful
                if (response.isSuccessful) {
                    loadPlantas()  // Atualiza a lista após a criação
                    _error.value = null
                } else {
                    _error.value = "Erro ao criar planta: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Erro ao criar planta: ${e.message}"
                _operationSuccess.value = false
            }
        }
    }

    // Atualiza uma planta existente
    fun updatePlanta(id: Int, planta: Planta) {
        viewModelScope.launch {
            try {
                val response = plantaRepository.updatePlanta(id, planta)
                _operationSuccess.value = response.isSuccessful
                if (response.isSuccessful) {
                    loadPlantas()  // Atualiza a lista após a atualização
                    _error.value = null
                } else {
                    _error.value = "Erro ao atualizar planta: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Erro ao atualizar planta: ${e.message}"
                _operationSuccess.value = false
            }
        }
    }

    // Exclui uma planta
    fun deletePlanta(id: Int) {
        viewModelScope.launch {
            try {
                val response = plantaRepository.deletePlanta(id)
                _operationSuccess.value = response.isSuccessful
                if (response.isSuccessful) {
                    loadPlantas()  // Atualiza a lista após a exclusão
                    _error.value = null
                } else {
                    _error.value = "Erro ao excluir planta: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Erro ao excluir planta: ${e.message}"
                _operationSuccess.value = false
            }
        }
    }

    // Limpa mensagens de erro após exibir
    fun clearError() {
        _error.value = null
    }

    // Limpa o status de sucesso da operação após exibir
    fun clearOperationStatus() {
        _operationSuccess.value = null
    }
}