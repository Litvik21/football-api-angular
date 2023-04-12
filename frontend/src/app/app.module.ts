import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { APP_DATE_FORMATS, AppDateAdapter } from './adapter/date.adapter';
import { PlayerComponent } from './player/player.component';
import { PlayerUpdateComponent } from './player-update/player-update.component';
import { PlayersInfoComponent } from './players-info/players-info.component';
import { PlayerTransferComponent } from './player-transfer/player-transfer.component';
import { TeamComponent } from './team/team.component';
import { TeamUpdateComponent } from './team-update/team-update.component';
import { TeamsInfoComponent } from './teams-info/teams-info.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayerComponent,
    PlayerUpdateComponent,
    PlayersInfoComponent,
    PlayerTransferComponent,
    TeamComponent,
    TeamUpdateComponent,
    TeamsInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule
  ],
  providers: [
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
