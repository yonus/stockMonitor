import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { StockAppModule } from './app.module';



platformBrowserDynamic().bootstrapModule(StockAppModule)
.then((success) => console.log(`Application started`))
.catch((err) => console.error(err));
