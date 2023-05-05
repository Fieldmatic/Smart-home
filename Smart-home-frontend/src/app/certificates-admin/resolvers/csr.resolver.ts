import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from '@angular/router';
import { filter, map, Observable, of, switchMap, take } from 'rxjs';
import { CSR } from '../model/CSR.model';
import { Store } from '@ngrx/store';
import { selectCSR } from '../store/certificates-admin.selectors';
import { Actions, ofType } from '@ngrx/effects';
import * as CertificatesAdminActions from '../store/certificates-admin.actions';

@Injectable({
  providedIn: 'root',
})
export class CsrResolver implements Resolve<CSR> {
  constructor(
    private store: Store,
    private actions$: Actions<CertificatesAdminActions.CertificatesAdminActionsUnion>
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<CSR> | Promise<CSR> | CSR {
    const id = route.params['id'];
    return this.store.select(selectCSR(id)).pipe(
      take(1),
      switchMap((csr) => {
        if (csr) {
          return of(csr);
        }
        this.store.dispatch(CertificatesAdminActions.getPendingCSRs());
        return this.actions$.pipe(
          ofType(CertificatesAdminActions.setCSRs.type),
          take(1),
          map((action) => action.csrs),
          filter((csrs) => csrs.length > 0),
          map((csrs) => csrs[0])
        );
      })
    );
  }
}
