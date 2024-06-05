import { ProfessionDTO } from "@/entities/ProfessionDTO";
import axios from "axios";
import { ref } from "vue";

export function useFetchUpdateProfession() {

    const isUpdateProfessionLoading = ref(false);
    const httpError = ref("");

    const fetchUpdateProfession = async (profession: ProfessionDTO) => {

        isUpdateProfessionLoading.value = true;

        try {

            const requestBody = JSON.parse(JSON.stringify(profession));

            const response = await axios.put('http://localhost:8080/api/professions/update-profession', requestBody);

            if (response.status === 200) { httpError.value = ""; }
            
        }

        catch (error) { 

            if (axios.isAxiosError(error)) { httpError.value = error.response?.data.message; } 
            
            else { httpError.value = "Ooops, something went wrong!"; }
        
        }

        finally { isUpdateProfessionLoading.value = false; }

    };

    return { isUpdateProfessionLoading, httpError, fetchUpdateProfession };
}