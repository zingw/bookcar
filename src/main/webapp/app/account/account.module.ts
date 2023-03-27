import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { accountState } from './account.route';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(accountState)],
  declarations: [],
})
export class AccountModule {}
