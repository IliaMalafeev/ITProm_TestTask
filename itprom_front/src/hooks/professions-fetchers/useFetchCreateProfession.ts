import { ProfessionDTO } from "@/entities/ProfessionDTO";
import axios from "axios";
import { ref } from "vue";

export function useFetchCreateProfession() {

    const profession = ref({} as ProfessionDTO);
    const isCreateProfessionLoading = ref(false);
    const httpError = ref("");

    const fetchCreateProfession = async (profession: ProfessionDTO) => {

        isCreateProfessionLoading.value = true;

        try {

            const requestBody = JSON.parse(JSON.stringify(profession));

            const response = await axios.post('http://localhost:8080/api/professions/add-profession', requestBody);

            if (response.status === 201) { httpError.value = ""; }
            
        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { httpError.value = error.response?.data.message; } 
            
            else { httpError.value = "Ooops, something went wrong!"; }
        
        }

        finally { isCreateProfessionLoading.value = false; }

    };

    return { profession, isCreateProfessionLoading, httpError, fetchCreateProfession };
}