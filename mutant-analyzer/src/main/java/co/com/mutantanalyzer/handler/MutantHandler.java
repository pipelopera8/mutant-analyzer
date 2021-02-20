
package co.com.mutantanalyzer.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.mutantanalyzer.general.util.Utils;
import co.com.mutantanalyzer.model.Mutant;
import co.com.mutantanalyzer.service.MutantService;
import lombok.extern.slf4j.Slf4j;

/**
 * Descripción: Servicio encargado de manejar el acceso a la base dedatos
 * 
 * @author felipe.lopera
 *
 */
@RabbitListener(queues = "mutant_queue")
@Slf4j
public class MutantHandler {

	@Autowired
	private MutantService mutantService;

	@Autowired
	private Utils utils;

	@RabbitHandler
	public void receive(byte[] obj) {
		try {
			Mutant mutant = (Mutant) utils.byteToObject(obj);
			mutantService.saveMutant(mutant);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
