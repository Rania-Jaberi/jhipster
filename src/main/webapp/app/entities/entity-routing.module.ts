import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'blog',
        data: { pageTitle: 'constatApp.blog.home.title' },
        loadChildren: () => import('./blog/blog.module').then(m => m.BlogModule),
      },
      {
        path: 'entry',
        data: { pageTitle: 'constatApp.entry.home.title' },
        loadChildren: () => import('./entry/entry.module').then(m => m.EntryModule),
      },
      {
        path: 'tag',
        data: { pageTitle: 'constatApp.tag.home.title' },
        loadChildren: () => import('./tag/tag.module').then(m => m.TagModule),
      },
      {
        path: 'test-farouk',
        data: { pageTitle: 'constatApp.testFarouk.home.title' },
        loadChildren: () => import('./test-farouk/test-farouk.module').then(m => m.TestFaroukModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
