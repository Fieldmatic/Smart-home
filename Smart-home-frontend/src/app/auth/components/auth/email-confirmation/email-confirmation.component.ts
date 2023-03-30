import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { confirm_email } from '../../../store/auth.actions';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-email-confirmation',
  templateUrl: './email-confirmation.component.html',
  styleUrls: ['./email-confirmation.component.scss'],
})
export class EmailConfirmationComponent implements OnInit {
  constructor(private store: Store, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const token = this.route.snapshot.params['token'];
    this.store.dispatch(confirm_email({ token }));
  }
}
