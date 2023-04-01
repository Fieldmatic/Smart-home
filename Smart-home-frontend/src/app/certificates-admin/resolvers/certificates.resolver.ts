import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Certificate } from '../model/certificate.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as CertificatesAdminActions from '../store/certificates-admin.actions';
import { selectCertificates } from '../store/certificates-admin.selectors';

@Injectable({
  providedIn: 'root',
})
export class CertificatesResolver implements Resolve<Certificate[]> {
  constructor(
    private store: Store,
    private actions$: Actions<CertificatesAdminActions.CertificatesAdminActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Certificate[]> | Promise<Certificate[]> | Certificate[] {
    return this.store.select(selectCertificates).pipe(
      take(1),
      switchMap((certificates) => {
        if (certificates.length > 0) {
          return of(certificates);
        }
        this.store.dispatch(CertificatesAdminActions.get_certificates());
        return this.actions$.pipe(
          ofType(CertificatesAdminActions.set_certificates.type),
          take(1),
          map((action) => action.certificates)
        );
      })
    );
  }
}
