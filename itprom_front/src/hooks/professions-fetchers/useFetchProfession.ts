import { onMounted, ref } from "vue";
import { ProfessionDTO } from "@/entities/ProfessionDTO";
import axios from "axios";

export function useFetchProfession() {

    const profession = ref({} as ProfessionDTO);
    const isProfessionLoading = ref(true);
    const isProfessionFound = ref(false);

    const fetchProfession = async () => {

        try {
                    
            const professionId = (window.location.href).split('/')[5];

            const response = await axios.get(`http://localhost:8080/api/professions/${professionId}`);

            if (response.data === '') {
                isProfessionFound.value = false;
                return;
            }

            profession.value = response.data;
            isProfessionFound.value = true;                
            
        } 
        
        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        } 
        
        finally { isProfessionLoading.value = false; }

    };

    onMounted(fetchProfession);

    return { profession, isProfessionLoading, isProfessionFound, fetchProfession };

}