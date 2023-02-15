import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/model/Usuario';
import { UsuarioService } from 'src/app/service/usuario.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-usuario-edit',
  templateUrl: './usuario-edit.component.html',
  styleUrls: ['./usuario-edit.component.css']
})
export class UsuarioEditComponent implements OnInit{

  usuario: Usuario = new Usuario()

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  )
  {

  }
  ngOnInit() {
    if(environment.token == '')
      {
        //alert('Usuário deslogado, faça o login novamente')
        this.router.navigate(['/entrar'])
      }

      let id = this.route.snapshot.params['id']
      this.findByIdUsuario(id)
    }

    findByIdUsuario(id: number)
    {
      this.usuarioService.getByIdUsuario(id).subscribe((resposta: Usuario)=>{
        this.usuario = resposta
      })
    }

    atualizar()
    {
      this.usuarioService.putUsuario(this.usuario).subscribe((resposta: Usuario) =>{
        this.usuario = resposta
        console.log(resposta)
        alert('Usuário atualizado com sucesso!')
        this.usuario = new Usuario
        this.router.navigate(['/usuario'])
      })
    }

  }

