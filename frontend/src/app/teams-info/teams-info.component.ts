import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Team } from '../model/team';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-teams-info',
  templateUrl: './teams-info.component.html',
  styleUrls: ['./teams-info.component.scss']
})
export class TeamsInfoComponent implements OnInit {
  teams: Team[] = [];
  constructor(private teamService: TeamService,
              private router: Router) {
  }

  ngOnInit() {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getTeams()
      .subscribe(teams => {
        this.teams = teams
        console.log(this.teams)
      });
  }

  update(teamId: any) {
    this.router.navigate(['/teams', teamId]);
  }

  deleteTeam(teamId: any) {
    this.teamService.removeTeam(teamId).subscribe(() => {
      this.teams = this.teams.filter(team => team.id !== teamId);
    });
  }
}
