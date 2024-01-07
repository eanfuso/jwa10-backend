package edu.it.components;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.GsonBuilder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	private static Long obtenerTSactual() {
        return System.currentTimeMillis() / 1000;
    }
	private static byte[] obtenerFirma() {
        byte[] firma = new byte[] {
            (byte)214,(byte)316,
            (byte)22,(byte)66,(byte)54,(byte)32,(byte)32,(byte)21,
            (byte)44,(byte)55,(byte)65,(byte)43,(byte)23,(byte)56,(byte)98,(byte)76,
            (byte)32,(byte)223,(byte)54,(byte)32,(byte)32,(byte)15,(byte)11
        };
        return firma;
    }
	public static String crearJWT(String usuario) {
        Long iat = obtenerTSactual();
        Long exp = iat + 900;

        String jwt = Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS256, obtenerFirma())
                .setHeaderParam("type", "jwt")
                .setHeaderParam("alg", "HS256")
                .setSubject(usuario)
                .claim("iat", String.valueOf(iat))
                .claim("exp", String.valueOf(exp))
                .claim("company", "educacacionit")
                .claim("roles", "ROLE_ADMIN")
                .claim("idCliente", usuario)
                .compact();

        return jwt;
    }
	public static Object obtenerKey(String token, String key) {
        String[] splittedToken = token.split("\\.");

        RuntimeException tokenNoValido =
                new RuntimeException("El token no es valido");
        RuntimeException keyInvalida =
                new RuntimeException(String.join(" ", "La key",key,"no es valida"));

        if (splittedToken.length != 3) {
            throw tokenNoValido;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        com.google.gson.Gson gson = gsonBuilder.create();

        byte[] payload = Base64.decodeBase64(splittedToken[1]);
        try {
            String segundoCuerpo = new String(payload ,"UTF-8");
            System.out.println(segundoCuerpo);
            Map<String, Object> mapa = gson.fromJson(segundoCuerpo, Map.class);

            if (!mapa.containsKey(key)) {
                throw keyInvalida;
            }
            return mapa.get(key);
        }
        catch (Exception ex) {
            throw tokenNoValido;
        }
    }
    public static String obtenerIdCliente(String token) {
        return obtenerKey(token, "idCliente").toString();
    }
    public static boolean verificarSiEstaVencido(String token) {
        Long tsExp = Long.parseLong(obtenerKey(token, "exp").toString());
        System.out.println(tsExp);
        Long tsActual = obtenerTSactual();

        return (tsActual < tsExp);
    }
    public static void validarToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(obtenerFirma())
            .parseClaimsJws(token).getBody();
    }
	
}
