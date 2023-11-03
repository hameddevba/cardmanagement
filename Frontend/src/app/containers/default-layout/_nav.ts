import { INavData } from '@coreui/angular';
import { Role } from 'src/app/shared/role';
export interface NavData extends INavData {
  role?: Role[];
}
export const navItems: NavData[] = [
  {
    name: 'Gestion des utilisateurs',
    url: '/gestionUtilisateurs',
    iconComponent: { name: 'cil-arrow-thick-from-left' },
    role: [Role.ADMIN],
  },
  {
    name: 'Gestion des demandes',
    url: '/requests',
    iconComponent: { name: 'cil-arrow-thick-from-left' },
    role: [Role.ADMIN],
  },
];
