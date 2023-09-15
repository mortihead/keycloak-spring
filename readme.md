1) Download KeyCloak https://www.keycloak.org/downloads (22.0.3)
   Unzip in any folder
2) Start: ./kc.sh start-dev --http-port=8484
3) open http://localhost:8080
----
KeyCloak:
1) Create a Realm "SpringBootKeycloak"
2) Create client "my_app"\
   Valid redirect url: http://localhost:8081/* \
   Client authentication: Off (access type = public) \
   Authentication flow: Standard flow, Direct access grants
3) Create role "role_1"
4) Create user user1 with role mapping role_1 and password "Qwe123"
5) Test token:   
   POST
   http://localhost:8484/realms/SpringBootKeycloak/protocol/openid-connect/token \
   Body x-form-urlencoded:
   
   | Key   |      Value     |
   |----------|:-------------:|
   | client_id |my_app| 
   | username |user1| 
   | password |Qwe123|   
Decode token on https://jwt.io/

5) Run KeyCloakSpringApplication and go http://localhost:8081

