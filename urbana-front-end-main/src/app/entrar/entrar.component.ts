import { environment } from './../../environments/environment.development';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../model/UserLogin';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit{

  userLogin: UserLogin = new UserLogin()

  constructor(
    private auth: AuthService,
    private router: Router
  )
  {

  }
  ngOnInit() {
    window.scroll(0,0)
  }

  entrar()
  {
    this.auth.entrar(this.userLogin).subscribe((resposta: UserLogin) =>{
      this.userLogin = resposta

      environment.token = this.userLogin.token
      environment.nome = this.userLogin.nome
      environment.id = this.userLogin.id



      console.log(environment.token)
      console.log(environment.nome)
      console.log(environment.id)

      this.router.navigate(['/inicio'])
    }, error =>{
      if(error.status == 401){
          alert('Usuário ou senha estão incorretas!')
      }
    })
  }
}
