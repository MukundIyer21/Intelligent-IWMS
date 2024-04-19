package com.example.intelligentiwms.ui.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.intelligentiwms.databinding.FragmentHomeBinding
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intelligentiwms.WarehouseData
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.InputStreamReader

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val csvLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            val csvData = readCsvFile(uri)
            sendDataToServer(csvData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCSV.setOnClickListener {
            csvLauncher.launch("text/csv")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun readCsvFile(uri: Uri): MutableList<Array<String>>? {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val csvReader = CSVReader(InputStreamReader(inputStream))
        return csvReader.readAll()
    }
    private fun sendDataToServer(csvData: MutableList<Array<String>>?) {
        val warehouseData = getDataFromSharedPreferences()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://your-api-base-url.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.sendData(warehouseData)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Handle the successful response
                    } else {
                        // Handle the error case
                    }
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
    private fun getDataFromSharedPreferences(): WarehouseData {
        val sharedPreferences = requireContext().getSharedPreferences("your_pref_name", Context.MODE_PRIVATE)

        val warehouseName = sharedPreferences.getString("warehouseName", null)
        val ownerEmail = sharedPreferences.getString("ownerEmail", null)
        val password = sharedPreferences.getString("password", null)
        val warehouseLength = sharedPreferences.getInt("warehouseLength", 0)
        val wareHouseWidth = sharedPreferences.getInt("wareHouseWidth", 0)
        val wareHouseHeight = sharedPreferences.getInt("wareHouseHeight", 0)
        val entranceX = sharedPreferences.getInt("Entrance_x", 0)
        val entranceY = sharedPreferences.getInt("Entrance_y", 0)
        val entranceZ = sharedPreferences.getInt("Entrance_z", 0)
        val blockedX = sharedPreferences.getInt("Blocked_x", 0)
        val blockedY = sharedPreferences.getInt("Blocked_y", 0)
        val blockedZ = sharedPreferences.getInt("Blocked_z", 0)
        val blockedLength = sharedPreferences.getInt("BlockedLength", 0)
        val blockedWidth = sharedPreferences.getInt("BlockedWidth", 0)
        val blockedHeight = sharedPreferences.getInt("BlockedHeight", 0)
        val pathStartX = sharedPreferences.getInt("PathStart_x", 0)
        val pathStartY = sharedPreferences.getInt("PathStart_y", 0)
        val pathEndX = sharedPreferences.getInt("PathEnd_x", 0)
        val pathEndY = sharedPreferences.getInt("PathEnd_y", 0)

        return WarehouseData(
            warehouseName = warehouseName,
            ownerEmail = ownerEmail,
            Password = password,
            warehouseLength = warehouseLength,
            wareHouseWidth = wareHouseWidth,
            wareHouseHeight = wareHouseHeight,
            Entrance_x = entranceX,
            Entrance_y = entranceY,
            Entrance_z = entranceZ,
            Blocked_x = blockedX,
            Blocked_y = blockedY,
            Blocked_z = blockedZ,
            BlockedLength = blockedLength,
            BlockedWidth = blockedWidth,
            BlockedHeight = blockedHeight,
            PathStart_x = pathStartX,
            PathStart_y = pathStartY,
            PathEnd_x = pathEndX,
            PathEnd_y = pathEndY
        )
    }
}
    interface ApiService {
        @POST("/path/to/your/endpoint")
        suspend fun sendData(@Body requestBody: WarehouseData): Response<ResponseBody>
    }