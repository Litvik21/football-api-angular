import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerUpdateComponent } from './player-update/player-update.component';
import { PlayerComponent } from './player/player.component';
import { PlayersInfoComponent } from './players-info/players-info.component';
import { TeamsInfoComponent } from './teams-info/teams-info.component';
import { TeamComponent } from './team/team.component';
import { TeamUpdateComponent } from './team-update/team-update.component';
import { PlayerTransferComponent } from './player-transfer/player-transfer.component';

const routes: Routes = [
  { path: 'players/transfer/:id', component: PlayerTransferComponent },
  {path: 'players/:id', component: PlayerUpdateComponent},
  {path: 'players', component: PlayerComponent},
  {path: 'players-info', component: PlayersInfoComponent},

  {path: 'teams/:id', component: TeamUpdateComponent},
  {path: 'teams', component: TeamComponent},
  {path: 'teams-info', component: TeamsInfoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
