import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/model/Usuario';
import { UsuarioService } from 'src/app/service/usuario.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-usuario-delete',
  templateUrl: './usuario-delete.component.html',
  styleUrls: ['./usuario-delete.component.css']
})
export class UsuarioDeleteComponent implements OnInit {

  usuario: Usuario = new Usuario()
  id: number

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  ){}

  ngOnInit(){

    if(environment.token == '')
    {
      //alert('Usuário deslogado, faça o login novamente')
      this.router.navigate(['/entrar'])
    }

    this.id = this.route.snapshot.params['id']
    this.findByIdUsuario(this.id)
  }
  findByIdUsuario(id: number)
  {
    this.usuarioService.getByIdUsuario(id).subscribe((resp: Usuario) => {
      this.usuario = resp
    })
  }
  apagar()
  {
    this.usuarioService.deleteUsuario(this.id).subscribe(() =>{
      alert('Usuário apagado com sucesso!')
      this.router.navigate(['/usuario'])
    })
  }

}
