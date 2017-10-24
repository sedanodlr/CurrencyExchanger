package sedano.com.currencyexchanger.injection.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by luissedano on 20/3/17.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}