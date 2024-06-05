import router from "@/router";
import axios from "axios";

export function useFetchDeleteProfession() {

    const fetchDeleteProfession = async () => {

        try {

            const professionId = (window.location.href).split('/')[5];

            const response = await axios.delete(`http://localhost:8080/api/professions/delete-profession/${professionId}`);

            if (response.status === 204) { router.replace('/professions'); }
            
        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }

    };

    return { fetchDeleteProfession };

}