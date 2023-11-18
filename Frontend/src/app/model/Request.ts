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

export interface RequestValidation {
  account: string;
  user: string;
  cardType: string;
  card: string;
  observation: string;
  creationDate: string;
  rejectionReason: string;
  cardLimit: string;
  nameOnCard: string;
  smsLang: string;
  smsSentDate: string;
  renewMonth: string;
}

export const emptyRequestValidation = (): RequestValidation => {
  return {
    account: '',
    user: '',
    cardType: '',
    card: '',
    observation: '',
    creationDate: '',
    rejectionReason: '',
    cardLimit: '',
    nameOnCard: '',
    smsLang: '',
    smsSentDate: '',
    renewMonth: '',
  } as RequestValidation;
};
