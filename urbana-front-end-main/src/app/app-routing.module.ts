import { UsuarioDeleteComponent } from './delete/usuario-delete/usuario-delete.component';
import { UsuarioEditComponent } from './edit/usuario-edit/usuario-edit.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { EntrarComponent } from './entrar/entrar.component';
import { InicioComponent } from './inicio/inicio.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { CartaoEditComponent } from './edit/cartao-edit/cartao-edit.component';
import { CartaoDeletComponent } from './delete/cartao-delet/cartao-delet.component';

const routes: Routes = [
  {path: '', redirectTo: 'entrar', pathMatch: 'full'},
  {path: 'entrar', component: EntrarComponent},
  {path: 'cadastrar', component: CadastrarComponent},

  {path: 'inicio', component: InicioComponent},
  {path: 'usuario', component: UsuarioComponent},
  {path: 'usuario-edit/:id', component: UsuarioEditComponent},
  {path: 'usuario-delete/:id', component: UsuarioDeleteComponent},
  {path: 'cartao-edit/:id', component: CartaoEditComponent},
  {path: 'cartao-delet/:id', component: CartaoDeletComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
