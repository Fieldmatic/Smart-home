import { ActionReducerMap } from '@ngrx/store';
import * as fromAuth from '../../auth/store/auth.reducer';
import * as fromCertificatesAdmin from '../../certificates-admin/store/certificates-admin.reducer';

interface State {
  auth: fromAuth.State;
  security_admin: fromCertificatesAdmin.State;
}

export const reducers: ActionReducerMap<State> = {
  auth: fromAuth.reducer,
  security_admin: fromCertificatesAdmin.reducer,
};
