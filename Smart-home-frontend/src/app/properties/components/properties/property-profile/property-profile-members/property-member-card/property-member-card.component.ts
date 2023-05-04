import { Component, Input } from '@angular/core';
import { User } from '../../../../../../users/model/user.model';
import { Store } from '@ngrx/store';
import { removePropertyMember } from '../../../../../store/properties.actions';

@Component({
  selector: 'app-property-member-card',
  templateUrl: './property-member-card.component.html',
  styleUrls: ['./property-member-card.component.scss'],
})
export class PropertyMemberCardComponent {
  @Input() propertyId!: string;
  @Input() member!: User;
  @Input() isOwner!: boolean;

  constructor(private store: Store) {}

  removeMember() {
    this.store.dispatch(
      removePropertyMember({
        propertyId: this.propertyId,
        userId: this.member.id,
      })
    );
  }
}
