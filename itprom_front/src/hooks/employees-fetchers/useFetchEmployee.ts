import { onMounted, ref } from "vue";
import { Employee } from "@/entities/Employee";
import axios from "axios";
import { ProfessionDTO } from "@/entities/ProfessionDTO";
import { DepartmentDTO } from "@/entities/DepartmentDTO";

export function useFetchEmployee() {

    const employee = ref({} as Employee);
    const profession = ref({} as ProfessionDTO);
    const department = ref({} as DepartmentDTO);
    const isEmployeeLoading = ref(true);
    const isEmployeeFound = ref(false);

    const fetchEmployee = async () => {

        try {
                    
            const employeeId = (window.location.href.split('/')[5]);

            const response = await axios.get(`http://localhost:8080/api/employees/${employeeId}`);            

            if (response.data === '') {
                isEmployeeFound.value = false;
                return;
            }

            employee.value = response.data;
            profession.value = employee.value.professionDTO;
            department.value = employee.value.departmentDTO;
            isEmployeeFound.value = true;                
            
        } 
        
        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        } 
        
        finally { isEmployeeLoading.value = false; }

    };

    onMounted(fetchEmployee);

    return { employee, profession, department, isEmployeeLoading, isEmployeeFound, fetchEmployee };

}