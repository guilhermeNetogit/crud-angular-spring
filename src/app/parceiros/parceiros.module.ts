import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatTableModule } from '@angular/material/table';
import { SharedModule } from '../shared/shared-module';
import { ParceirosRoutingModule } from './parceiros-routing.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, ParceirosRoutingModule, MatTableModule, SharedModule],
})
export class ParceirosModule {}
