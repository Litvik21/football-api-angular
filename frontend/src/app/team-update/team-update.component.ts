import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player';
import { Team } from '../model/team';
import { FormBuilder } from '@angular/forms';
import { PlayerService } from '../service/player.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-team-update',
  templateUrl: './team-update.component.html',
  styleUrls: ['./team-update.component.scss']
})
export class TeamUpdateComponent implements OnInit {
  team: any;
  teams: Team[] = [];
  title = '';
  country = '';
  city = '';
  balance = 0;
  commission = 0;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.getTeam();
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams);
  }

  getTeam(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.teamService.getTeam(id)
      .subscribe(team => {
        this.team = team;
        this.title = team.title;
        this.country = team.country;
        this.city = team.city;
        this.balance = team.balance;
        this.commission = team.commission;
      });
  }

  updateTeam(): void {
    this.team = {
      id: this.team.id,
      title: this.title != '' ? this.title : this.team.title,
      country: this.country != '' ? this.country : this.team.country,
      city: this.city != '' ? this.city : this.team.city,
      balance: this.balance != 0 ? this.balance : this.team.balance,
      commission: this.commission != 0 ? this.commission : this.team.commission
    };

    this.teamService.updateTeam(this.team)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

}
