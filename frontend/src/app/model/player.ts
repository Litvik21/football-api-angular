import { Team } from "./team";

export interface Player {
  id: number;
  firstName: string;
  secondName: string;
  birthDate: Date;
  startCareer: Date;
  team: Team;
}
