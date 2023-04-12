import { Team } from "./team";

export interface Player {
  id: number;
  firstName: string;
  lastName: string;
  birthDate: Date;
  startCareer: Date;
  team: Team;
}
