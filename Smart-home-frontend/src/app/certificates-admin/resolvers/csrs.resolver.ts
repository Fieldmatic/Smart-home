import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as CertificatesAdminActions from '../store/certificates-admin.actions';
import { CSR } from '../model/CSR.model';

@Injectable({
  providedIn: 'root',
})
export class CsrsResolver implements Resolve<CSR[]> {
  constructor(
    private store: Store,
    private actions$: Actions<CertificatesAdminActions.CertificatesAdminActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<CSR[]> | Promise<CSR[]> | CSR[] {
    this.store.dispatch(CertificatesAdminActions.getPendingCSRs());
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.setCSRs.type),
      take(1),
      map((action) => action.csrs)
    );
  }
}
