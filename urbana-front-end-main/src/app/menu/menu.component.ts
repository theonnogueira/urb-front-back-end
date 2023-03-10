import { Router } from '@angular/router';
import { environment } from './../../environments/environment.development';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  nome = environment.nome


  constructor(
    private router: Router
  ){}

  ngOnInit()
  {}

  sair()
  {
    this.router.navigate(['/entrar'])
    environment.token = ''
    environment.nome = ''
    environment.id = 0
  }
}
