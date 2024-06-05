import { onMounted, ref } from "vue";
import { ProfessionDTO } from "@/entities/ProfessionDTO";
import axios from "axios";

export function useFetchProfessions() {
    
    const professions = ref([] as ProfessionDTO[]);
    const isProfessionsLoading = ref(true);

    const fetchProfessions = async () => {

        try {

            const response = await axios.get('http://localhost:8080/api/professions');

            if (response.status !== 200) { throw new Error(); }

            professions.value = response.data;

        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }

        finally { isProfessionsLoading.value = false; }

    };

    onMounted(fetchProfessions);

    return { professions, isProfessionsLoading, fetchProfessions };
}