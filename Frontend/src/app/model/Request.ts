import { User } from '../shared/user';
import { Account } from './Account';
import { CardType } from './CardType';

export interface Request {
  id: number;
  account?: Account;
  user: User;
  cardType?: CardType;
  card: string;
  observation: string;
  creationDate: Date;
  rejectionReason: string;
  cardLimit: number;
  nameOnCard: string;
  smsLang: string;
  smsSentDate: Date;
  renewMonth: number;
}
