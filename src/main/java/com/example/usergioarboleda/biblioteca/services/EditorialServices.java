/**
 * <p>Copyright: Copyright (c) 2022</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2022 by Carlos Andres Sierra Virguez. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="https://www.linkedin.com/in/casierrav/"> Carlos Andres Sierra </A>
 * (E-mail: <A HREF="mailto:carlos.sierra1.mt@usa.edu.co">carlos.sierra1.mt@usa.edu.co</A> )
 * @version 1.0
 */

package com.example.usergioarboleda.biblioteca.services;

import com.example.usergioarboleda.biblioteca.models.Editorial;
import com.example.usergioarboleda.biblioteca.repositories.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author casierrav
 */
@Service
public class EditorialServices {
    @Autowired
    private EditorialRepository editorialRepository;

    /**
     *
     * @return
     */
    public List<Editorial> getAllEditorials(){
        return editorialRepository.getAll();
    }

    /**
     *
     * @param country
     * @return
     */
    public List<Editorial> getAllEditorialsByCountry(String country){
        return editorialRepository.getAllByCountry(country);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Editorial> getEditorial(int id){
        return editorialRepository.getEditorial(id);
    }

    /**
     *
     * @param name
     * @return
     */
    public Optional<Editorial> getEditorialByName(String name){
        return editorialRepository.getEditorialByName(name);
    }

    /**
     *
     * @param editorial
     * @return
     */
    public Editorial insertEditorial(Editorial editorial){
        if(editorial.getIdEditorial() == null)
            return editorialRepository.save(editorial);
        else
            return editorial;
    }

    /**
     *
     * @param editorial
     * @return
     */
    public Editorial updateEditorial(Editorial editorial){
        if(editorial.getIdEditorial() != null){
            Optional<Editorial> temp = editorialRepository.getEditorial( editorial.getIdEditorial() );
            if( !temp.isEmpty() ){
                if(editorial.getName() != null)
                    temp.get().setName( editorial.getName() );
                if(editorial.getCountry() != null)
                    temp.get().setCountry( editorial.getCountry() );
                return editorialRepository.save(temp.get());
            }
            else
                return editorial;
        }
        else
            return editorial;
    }

    /**
     *
     * @param editorialId
     */
    public boolean deleteEditorial(int editorialId){
        Boolean success = getEditorial(editorialId).map(editorial -> {
            editorialRepository.delete(editorial);
            return true;
        }).orElse(false);
        return success;
    }
}