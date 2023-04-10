import {Player} from "./player";

export interface Team {
  id: number;
  title: string;
  country: string;
  city: string;
  balance: number;
  commission: number;
  players?: Player[];
}
