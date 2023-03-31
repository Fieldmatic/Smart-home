import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Extension } from '../model/extension.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as CertificatesAdminActions from '../store/certificates-admin.actions';
import { selectCertificateExtensions } from '../store/certificates-admin.selectors';

@Injectable({
  providedIn: 'root',
})
export class CertificateExtensionsResolver implements Resolve<Extension[]> {
  constructor(
    private store: Store,
    private actions$: Actions<CertificatesAdminActions.CertificatesAdminActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Extension[]> | Promise<Extension[]> | Extension[] {
    return this.store.select(selectCertificateExtensions).pipe(
      take(1),
      switchMap((certificateExtensions) => {
        if (certificateExtensions.length > 0) {
          return of(certificateExtensions);
        }
        this.store.dispatch(
          CertificatesAdminActions.get_certificate_extensions()
        );
        return this.actions$.pipe(
          ofType(CertificatesAdminActions.set_certificate_extensions.type),
          take(1),
          map((action) => action.certificateExtensions)
        );
      })
    );
  }
}
