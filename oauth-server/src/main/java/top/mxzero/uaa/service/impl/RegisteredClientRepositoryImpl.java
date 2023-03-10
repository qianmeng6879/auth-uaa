package top.mxzero.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import top.mxzero.uaa.entity.Client;
import top.mxzero.uaa.exception.ServiceException;
import top.mxzero.uaa.mapper.ClientMapper;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
public class RegisteredClientRepositoryImpl implements RegisteredClientRepository, ApplicationContextAware {
    private ClientMapper mapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        System.out.println(registeredClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        if (!id.matches("\\d+")) {
            return null;
        }

        Client client = mapper.selectById(Long.parseLong(id));

        return createRegisteredClient(client);

    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = mapper.selectOne(new QueryWrapper<Client>().eq("client_id", clientId));
        if (client == null) {
            throw new ServiceException("client_id不存在");
        }
        return createRegisteredClient(client);
    }

    private RegisteredClient createRegisteredClient(Client client) {
        System.out.println(client.toString());
        RegisteredClient registeredClient = RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_JWT)
                .clientAuthenticationMethod(ClientAuthenticationMethod.PRIVATE_KEY_JWT)
                .redirectUri(client.getRedirectUri())
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope(client.getScope())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .build();
        return registeredClient;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.mapper = applicationContext.getBean(ClientMapper.class);
    }
}
