import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { CertificateType } from '../model/certificate-type.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as CertificatesAdminActions from '../store/certificates-admin.actions';
import { selectCertificateTypes } from '../store/certificates-admin.selectors';

@Injectable({
  providedIn: 'root',
})
export class CertificateTypesResolver implements Resolve<CertificateType[]> {
  constructor(
    private store: Store,
    private actions$: Actions<CertificatesAdminActions.CertificatesAdminActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<CertificateType[]>
    | Promise<CertificateType[]>
    | CertificateType[] {
    return this.store.select(selectCertificateTypes).pipe(
      take(1),
      switchMap((certificateTypes) => {
        if (certificateTypes.length > 0) {
          return of(certificateTypes);
        }
        this.store.dispatch(CertificatesAdminActions.getCertificateTypes());
        return this.actions$.pipe(
          ofType(CertificatesAdminActions.setCertificateTypes.type),
          take(1),
          map((action) => action.certificateTypes)
        );
      })
    );
  }
}
