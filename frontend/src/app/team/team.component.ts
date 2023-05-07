import { Component, OnInit } from '@angular/core';
import { TeamService } from '../service/team.service';
import { Team } from '../model/team';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {
  teams: Team[] = [];
  title = '';
  country = '';
  city = '';
  balance = 0;
  commission = 0;

  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams);
  }

  add(): void {
    let id = Math.max.apply(Math, this.teams.map(function (t) {return t.id;}));

    this.teamService.addTeam({id: id + 1, title: this.title, country: this.country,
      city: this.city, balance: this.balance, commission: this.commission} as Team)
      .subscribe(team => {this.teams.push(team)});

    this.resetForm();
  }

  resetForm() {
    this.title = '';
    this.country = '';
    this.city = '';
    this.balance = 0;
    this.commission = 0;
  }

}
