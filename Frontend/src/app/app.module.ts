import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

import { FlexLayoutModule } from '@angular/flex-layout';

import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { NgModule } from '@angular/core';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; 
import { PlatsearchpipePipe } from './Pipe/platsearchpipe.pipe';


/**     @angular/material          */
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatPaginatorModule} from '@angular/material/paginator';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSnackBarModule } from '@angular/material/snack-bar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatSelectModule} from '@angular/material/select';
import {MatStepperModule} from '@angular/material/stepper';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatRadioModule} from '@angular/material/radio';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import {MatTooltipModule} from '@angular/material/tooltip';







/**                   Components            */

import { ToolbarComponent } from './Component/toolbar/toolbar.component';
import { WishComponent } from './Component/wish/wish.component';
import { CartComponent } from './Component/cart/cart.component';
import { OrdergreetingComponent } from './Component/ordergreeting/ordergreeting.component';
import { PagenotfoundComponent } from './Component/pagenotfound/pagenotfound.component';
import { ForgetPasswordComponent } from './Component/auth/forget-password/forget-password.component';
import { ResetPasswordComponent } from './Component/auth/reset-password/reset-password.component';

import { SpineerComponent } from './Component/spineer/spineer.component';
import { LoginComponentComponent } from './Component/auth/login-component/login-component.component';
import { RegistrationComponent } from './Component/auth/registration/registration.component';
import { SellerComponent } from './Component/seller/seller/seller.component';
import { FooterComponent } from './Component/footer/footer.component';
import { DashboardComponent } from './Component/dashboard/dashboard.component';
import { SidenavbarComponent } from './Component/sidenavbar/sidenavbar.component';
import { GiverateComponent } from './Component/giverate/giverate.component';
import { RatereviewComponent } from './Component/ratereview/ratereview.component';
import { OrderstatusComponent } from './Component/orderstatus/orderstatus.component';
import { HomeComponent } from './Component/home/home.component';
import { CandidatureComponent } from './Component/home/candidature/candidature.component';
import { ConnectionComponent } from './Component/home/connection/connection.component';
import { PlatsComponent } from './Component/home/plats/plats.component';
import { HomeClientComponent } from './Component/home-client/home-client.component';
import { AccueilClientComponent } from './Component/home-client/accueil-client/accueil-client.component';
import { ChefsVisiteurComponent } from './Component/home/chefs-visiteur/chefs-visiteur.component';
import { ChefDeSemaineComponent } from './Component/home/chef-de-semaine/chef-de-semaine.component';


import { AddplatComponent } from './Component/addplat/addplat.component';
import { UploadPlatImageComponent } from './Component/addplat/upload-plat-image/upload-plat-image/upload-plat-image.component';


import { PlatreviewsComponent } from './Component/platreviews/platreviews/platreviews.component';
import { DisplayplatComponent } from './Component/displayplat/displayplat/displayplat.component';
import { RatedplatsComponent } from './Component/ratedplats/ratedplats.component';
import { UpdatePlatComponent } from './Component/update-plat/update-plat/update-plat.component';

import { AddchefComponent } from './Component/addchef/addchef.component';

import { UploadChefImageComponent } from './Component/addchef/upload-chef-image/upload-chef-image.component';
import { UpdateChefComponent } from './Component/update-chef/update-chef.component';
import { DisplaychefComponent } from './Component/displaychef/displaychef.component';
import { HomeSellerComponent } from './Component/home-seller/home-seller.component';
import { CandidaturesSellerComponent } from './Component/home-seller/candidatures-seller/candidatures-seller.component';
import { AccueilSellerComponent } from './Component/home-seller/accueil-seller/accueil-seller.component';












@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    PlatsearchpipePipe,
    CartComponent,
    OrdergreetingComponent,
    PagenotfoundComponent,
    ForgetPasswordComponent,
    ResetPasswordComponent,
    SpineerComponent,
    LoginComponentComponent,
    RegistrationComponent,
    SellerComponent,
    FooterComponent,
    DashboardComponent,
    SidenavbarComponent,
    GiverateComponent,
  
    RatereviewComponent,
    AddplatComponent,
    OrderstatusComponent,
    WishComponent,
    HomeComponent,
    CandidatureComponent,
    ConnectionComponent,
    PlatsComponent,
    HomeClientComponent,
    AccueilClientComponent,
    ChefsVisiteurComponent,
    ChefDeSemaineComponent,
    UploadPlatImageComponent ,
    PlatreviewsComponent,
    DisplayplatComponent,
    RatedplatsComponent,
    UpdatePlatComponent,

    AddchefComponent,
    UploadChefImageComponent,
    UpdateChefComponent,
    DisplaychefComponent,
    HomeSellerComponent,
    CandidaturesSellerComponent,
    AccueilSellerComponent,


  
  
    

  ],
  imports: [
    MatBadgeModule,
    BrowserModule,
    MatToolbarModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatPaginatorModule,
    FormsModule,
    MatCardModule,

    HttpClientModule,
    MatSnackBarModule,
    MatStepperModule,
    MatCheckboxModule,
    MatRadioModule,
    MatIconModule,
    FlexLayoutModule,
    MatMenuModule,
    MatButtonModule,
    MatSidenavModule,
    MatSelectModule,
    MatTooltipModule,
    MatDialogModule,
    ReactiveFormsModule 
   

    
 ],
  providers: [HttpClient],
  bootstrap: [AppComponent],

})
export class AppModule { }
