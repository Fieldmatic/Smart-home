import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable, of, switchMap, take } from 'rxjs';
import { selectRole } from './store/auth.selectors';
import { Store } from '@ngrx/store';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private store: Store, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.store.select(selectRole).pipe(
      take(1),
      switchMap((role) => {
        if (role === 'ADMIN') {
          return of(true);
        }
        return of(this.router.createUrlTree(['/']));
      })
    );
  }
}
