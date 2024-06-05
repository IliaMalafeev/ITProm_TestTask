import { DepartmentDTO } from "./DepartmentDTO";
import { ProfessionDTO } from "./ProfessionDTO";

export class Employee {

    id?: number;
    fullName: string;
    additionalInfo: string;
    professionDTO: ProfessionDTO;
    departmentDTO: DepartmentDTO;

    constructor (fullName: string, additionalInfo: string, professionDTO: ProfessionDTO, departmentDTO: DepartmentDTO, id?: number) {

        this.id = id;
        this.fullName = fullName;
        this.additionalInfo = additionalInfo;
        this.professionDTO = professionDTO;
        this.departmentDTO = departmentDTO;
    }
    
}