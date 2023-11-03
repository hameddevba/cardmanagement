import { Agency } from './Agency';

export interface Account {
  id: number;
  accountNumber: string;
  agency: Agency;
}
