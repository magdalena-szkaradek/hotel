import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RoomComponentComponent } from './room-component/room-component.component';
import { HeaderComponentComponent } from './header-component/header-component.component';
import { FooterComponent } from './footer/footer.component';
import { SliderComponentComponent } from './slider-component/slider-component.component';
import { SearchComponentComponent } from './search-component/search-component.component';
import {RouterModule, Routes} from '@angular/router';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
import { MainComponentComponent } from './components/main-component/main-component.component';
import {APP_BASE_HREF} from '@angular/common';
import { UserService } from './services/user.service';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { AdminAddUserComponent } from './components/admin-add-user/admin-add-user.component';

const appRoutes: Routes = [
  {path:'', component: MainComponentComponent},
  {path:'register', component: RegisterComponent},
  {path:'login', component: LoginComponent},
  {path:'logout', component: LogoutComponent},
  {path:'admin/users', component: AdminUsersComponent},
  {path:'admin/add-user', component: AdminAddUserComponent}

]

@NgModule({
  declarations: [
    AppComponent,
    RoomComponentComponent,
    HeaderComponentComponent,
    FooterComponent,
    SliderComponentComponent,
    SearchComponentComponent,
    MainComponentComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    AdminUsersComponent,
    AdminNavbarComponent,
    AdminAddUserComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    {provide: APP_BASE_HREF, useValue : '/' },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
