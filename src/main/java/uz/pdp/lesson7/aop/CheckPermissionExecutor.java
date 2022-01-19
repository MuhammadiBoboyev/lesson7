package uz.pdp.lesson7.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.exeptions.ForbiddenExeption;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
        for (GrantedAuthority authority : ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                return;
            }
        }
        throw new ForbiddenExeption(checkPermission.value(), "ruxsat yo'q");
    }
}
